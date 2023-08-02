package gitlet;
import gitlet.Dumpable.*;
import java.util.Date;
import java.util.Formatter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class stage_object implements Dumpable{
    public void dump() {

        String sha = Utils.sha1(Utils.serialize(this));


        File file = Utils.join(new File(System.getProperty("user.dir")), ".gitlet", ".stage");
        Utils.writeObject(Utils.join(file, sha), this);
    }
    public Date timestamp;
    public File file_ref;
    public String file_hash;

    public String file_name;
    public boolean remove = false;

    public stage_object(Date input_timestamp,
                        File input_file_ref,
                        String input_file_name){
        this.timestamp = input_timestamp;
        this.file_ref = input_file_ref;
        this.file_name = input_file_name;
    }
    public void setter_rm(boolean input_remove){
        this.remove = input_remove;
    }
    public void set_file_hash(String hash){
        this.file_hash = hash;
    }
}
