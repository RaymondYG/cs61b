package gitlet;
import gitlet.Dumpable.*;
import java.util.Date;
import java.util.Formatter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class stage_object implements Dumpable{
    public void dump() {}
    public Date timestamp;
    public String message;
    public File file_ref;
    public String file_blobs;
    public stage_object(){
        this.timestamp = new Date();
        this.timestamp.setTime(0);
        this.message = "initial commit";
        this.file_ref = null;
    }

    public stage_object(Date input_timestamp, String input_message, File input_file_ref){
        this.timestamp = input_timestamp;
        this.message = input_message;
        this.file_ref = input_file_ref;
    }
    public void set_file_blobs(String contents){
        this.file_blobs = contents;
    }
}
