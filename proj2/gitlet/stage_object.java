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
    public File file_ref;
    public String file_hash;

    public String file_name;


    public stage_object(Date input_timestamp,
                        File input_file_ref,
                        String input_file_name){
        this.timestamp = input_timestamp;
        this.file_ref = input_file_ref;
        this.file_name = input_file_name;
    }
    public void set_file_hash(String hash){
        this.file_hash = hash;
    }
}
