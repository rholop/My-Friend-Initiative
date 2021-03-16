package slides;

import config.Config;
import database.records.InsertRecords;
import database.records.RemoveRecords;
import database.records.RetrieveSoundRecords;
import database.records.UpdateRecords;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Sound {
    private String fileLocation;
    private int volume;
    private int ID;
    private Config config = new Config();
    public AudioClip player;

    /**
     * Constructor for the sound class.
     * @param fileLocation The file location of the sound
     * @param volume The volume of the sound
     * @param ID The ID of the sound in the database
     */
    public Sound(String fileLocation, int volume, int ID) {
        this.fileLocation = fileLocation;
        this.volume = volume;
        this.ID = ID;
    }

    /**
     * Overload Sound constructor for when we don't want to set the ID
     * @param fileLocation The file location of the sound
     * @param volume The volume of the sound
     */
    public Sound(String fileLocation, int volume) {
        this.fileLocation = fileLocation;
        this.volume = volume;
    }

    /**
     * Getter method for the file location of the sound
     * @return The file location of the sound
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * Getter method for the volume of the sound
     * @return The volume of the sound
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Getter method for the ID of the sound in the database
     * @return The ID of the sound in the database
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method for the file location
     * @param fileLocation The file location of the sound
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("file_location", fileLocation);
        System.out.println(fileLocation);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Sound", update, ID);
        }
    }

    /**
     * Setter method for the sound volume
     * @param volume The sound volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
        LinkedHashMap<String, Object> update = new LinkedHashMap<>();
        update.put("volume", volume);
        if (ID != -1) {
            UpdateRecords.UpdateRecord(config.getURL(), "Sound", update, ID);
        }
    }

    /**
     * Retrieves sound objects from the database
     * @param slideNumber the slide number to get the sound objects from
     * @return An ArrayList of Sound objects
     */
    public static ArrayList<Sound> getFromDB(int slideNumber) {
        Config config = new Config();
        ArrayList<LinkedHashMap<String, Object>> soundData =
                new RetrieveSoundRecords().selectAllFromSlide(config.getURL(), slideNumber);
        ArrayList<Sound> soundObjects = new ArrayList<>();
        for (LinkedHashMap<String, Object> sound : soundData) {
            Sound sound1 = new Sound((String)sound.get("file_location"), (int)sound.get("volume"), (int)sound.get("id"));
            soundObjects.add(sound1);
        }
        return soundObjects;
    }

    /**
     * Sets a record to the database with the object's variables
     * @param slideNumber The slide number to connect the object to
     */
    public void setToDB(int slideNumber) {
        InsertRecords.insertSound(config.getURL(), fileLocation, volume, slideNumber);
        String[] fields = {"id"};
        ID = (int)RetrieveSoundRecords.selectSome(config.getURL(), "Sound", fields).get("id");
    }

    public void removeFromDB() {
        RemoveRecords.remove(config.getURL(), ID, "Sound");
    }

    /**
     * Displays the sound on the slide
     */
    public void play(AudioClip mediaPlayer) {
        URI file = new File(fileLocation).toURI();
        Media media = new Media(file.toString());
        mediaPlayer = new AudioClip(media.getSource());
        mediaPlayer.play();
        player = mediaPlayer;
    }

    public void stop() {
        player.stop();
    }

    public boolean equals(Sound sound) {
        if (sound.ID == this.ID) {
            return true;
        }
        return false;
    }
}
