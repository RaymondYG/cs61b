package gitlet;
import gitlet.Utils.*;
import gitlet.stage_object.*;

import java.io.File;
import java.util.Date;
import java.util.List;

import static gitlet.Utils.join;
import static gitlet.Utils.readContentsAsString;

public class gitlet_add {
    private stage_object obj;
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGE_DIR = join(GITLET_DIR, ".stage");
    public gitlet_add(Date input_timestamp, File input_file_ref, String input_file_name){
        if(!input_file_ref.exists()){
            System.out.println("File does not exist.");
            System.exit(0);
        }
        obj = new stage_object(input_timestamp,input_file_ref,input_file_name);
    }
    public void set_remove(){
        obj.remove = true;
    }
    public void write_object(String file_hash, String file_content){
        obj.set_file_hash(file_hash);
        obj.dump();
        Utils.writeContents(join(STAGE_DIR,".file", file_hash), file_content);
    }
    public void add(){
        String file_content = readContentsAsString(obj.file_ref);
        String file_hash = Utils.sha1(file_content);

        if(STAGE_DIR.mkdir()){
            join(STAGE_DIR, ".file").mkdir();
            write_object(file_hash, file_content);
        }else{
            // current version is the same with the one in the stage, don't add it
            List<String> stage_obj_path_lst = Utils.plainFilenamesIn(STAGE_DIR);
            boolean add_state = true;
            for (String stage_obj_path : stage_obj_path_lst){
                stage_object obj_tmp = Utils.readObject(join(STAGE_DIR,stage_obj_path ), stage_object.class);
                if(obj_tmp.file_name.equals(this.obj.file_name)){
                    add_state = false;
                    if (obj_tmp.remove && obj.remove){
                        //nothing
                    } else if (obj_tmp.remove && (!obj.remove)) {
                        new File(stage_obj_path).delete();
                        write_object(file_hash, file_content);
                    } else if ((!obj_tmp.remove) && obj.remove) {
                        new File(stage_obj_path).delete();
                        write_object(file_hash, file_content);
                    } else{
                        if(! obj_tmp.file_hash.equals(file_hash)){
                            new File(stage_obj_path).delete();
                            write_object(file_hash, file_content);
                        }
                    }
                    break;
                }
            }
            if (add_state){
                write_object(file_hash, file_content);
            }
        }
    }
}
