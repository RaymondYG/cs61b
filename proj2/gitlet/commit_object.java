package gitlet;
import gitlet.Dumpable.*;

import java.util.*;
import java.io.File;

import gitlet.stage_object.*;

import static gitlet.Utils.join;

public class commit_object implements Dumpable{
    public void dump() {}
    public Date timestamp;
    public String message;
    public HashMap<String, String> file_ref_dict;
    public String parent_ref;
    public commit_object(Date input_timestamp,
                         String input_message,
                         String parent,
                         HashMap<String, String> file_dict
                         ){
        this.timestamp = input_timestamp;
        this.message = input_message;
        this.parent_ref = parent;
        this.file_ref_dict = file_dict;
    }
    public boolean update_by_stage(stage_object stage_add){
        setter_time(stage_add.timestamp);
        if(file_ref_dict == null){
            file_ref_dict = new HashMap<String, String>();
            file_ref_dict.put(stage_add.file_name, stage_add.file_hash);
            file_transfer(stage_add.file_hash);
            destroy_stage();
            return true;
        }
        else if(file_ref_dict.containsKey(stage_add.file_name)){
            if (!file_ref_dict.get(stage_add.file_name).equals(stage_add.file_hash)){
                file_ref_dict.put(stage_add.file_name, stage_add.file_hash);
                file_transfer(stage_add.file_hash);
                destroy_stage();
                return true;
            }else{
                destroy_stage();
                return false;
            }
        }else{
            file_ref_dict.put(stage_add.file_name, stage_add.file_hash);
            file_transfer(stage_add.file_hash);
            destroy_stage();
            return true;
        }


    }
    public void setter_time(Date time){
        this.timestamp = time;
    }
    public void setter_message(String message){
        this.message = message;
    }
    public static void file_transfer(String file_hash){
        File gitlet =join(new File(System.getProperty("user.dir")), ".gitlet");
        File stage = join(gitlet, ".stage");
        File file_folder = join(gitlet, ".file");
        file_folder.mkdir();
        File file_path = join(file_folder, file_hash);
        join(stage, file_hash).renameTo(file_path);
    }
    public static void destroy_stage(){
        File folder = join(new File(System.getProperty("user.dir")), ".gitlet",".stage");

        String[]entries = folder.list();
        for(String s: entries){
            File currentFile = new File(folder.getPath(),s);
            currentFile.delete();
        }
        folder.delete();
    }

}
