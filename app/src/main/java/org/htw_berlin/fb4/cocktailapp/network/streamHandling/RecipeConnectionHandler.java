package org.htw_berlin.fb4.cocktailapp.network.streamHandling;

import org.htw_berlin.fb4.cocktailapp.model.recipe.Recipe;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RecipeConnectionHandler implements StreamHandler {

    private String name;
    private int nextReadNumber = 0;
    private HashMap<Integer, RecipeReader> activeReader = new HashMap<>();
    private HashMap<Integer, OutputStream> activeOutputStreams = new HashMap<>();

    private List<String> usedIDs = new ArrayList<>();

    public RecipeConnectionHandler(String name) {
        this.name = name;
    }
        public void sendRecipe(List<Recipe> recipes){
            Collection<OutputStream> openConnections = this.activeOutputStreams.values();
            if (openConnections.size() > 0) {
                // create id - very very simple
                long time = System.currentTimeMillis();
                int random = new Random().nextInt();

                StringBuilder idBuilder = new StringBuilder();
                idBuilder.append(time);
                idBuilder.append(random);

                String id = idBuilder.toString();

                for (OutputStream os: openConnections) {
                try {
                    for (Recipe s : recipes) {
                        RecipePDU recipePDU = new RecipePDU(s,id);
                        recipePDU.writePDU(os);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
            }
        }

    @Override
    public void handleConnection(InputStream is, OutputStream os) throws IOException {
        int id = nextReadNumber++;
        this.activeReader.put(id, new RecipeReader(id, is));
        this.activeOutputStreams.put(id, os);
    }

    private synchronized void handlePDU(RecipePDU recipePDU, RecipeReader recipeReader) {
        // got pdu
        // already read this pdu?

        if (this.usedIDs.contains(recipePDU.id)) {
            System.out.println("Log: id already exists: " + recipePDU.id);
        } else {
            // now message do recipe
            System.out.println(this.name + ": got message: " + recipePDU);
            List<Integer> deadIDs = new ArrayList<>();
            for (Integer id : activeOutputStreams.keySet()) {
                if (recipeReader.id != id) {
                    // don't send back to sender but anybody else
                    try {
                        recipePDU.writePDU(activeOutputStreams.get(id));
                    } catch (IOException e) {
                        // problems with this output stream - remember
                        deadIDs.add(id);
                    }
                }
            }

            // remove dead connections
            for (Integer id : deadIDs) {
                this.activeOutputStreams.remove(id);
                RecipeReader reader = this.activeReader.remove(id);
                reader.kill();
            }
        }
    }
    private synchronized void inputStreamDead(int id) {
        // kill and remove output stream as well
        OutputStream os = this.activeOutputStreams.remove(id);

        try {
            os.close();
        } catch (IOException e) {
            // did my best
        }
    }

    private class RecipeReader extends Thread {
        private final InputStream is;
        private final int id;

        RecipeReader(int id, InputStream is) {
            this.id = id;
            this.is = is;
            this.start(); // start yourself
        }

        public void run() {
            boolean again = true;
            while (again) {
                try {
                    // read pdu
                    RecipePDU recipePDU = new RecipePDU(is);
                    // handle pdu
                    RecipeConnectionHandler.this.handlePDU(recipePDU, this);
                } catch (IOException | ClassNotFoundException e) {
                    // cannot recover from that.
                    RecipeConnectionHandler.this.inputStreamDead(id);
                    again = false; // end thread
                }
            }
        }

        public void kill() {
            try {
                this.is.close();
            } catch (IOException e) {
                // cannot do anything here - did my best
            }
        }
    }

    private class RecipePDU {
        private String id;
        private Recipe recipe;

        RecipePDU(Recipe recipe, String id) {
            this.recipe = recipe;
            this.id = id;
        }

        RecipePDU(InputStream is) throws IOException, ClassNotFoundException {
            ObjectInputStream ois = new ObjectInputStream(is);
            recipe = (Recipe) ois.readObject();
        }

        void writePDU(OutputStream os) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this.recipe);
            oos.writeObject(id);
            oos.flush();
        }

        public String toString() {
            return "ID: " + id + "\n" + recipe.toString();
        }
    }

}



