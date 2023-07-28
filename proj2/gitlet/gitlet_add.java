package gitlet;
import gitlet.Utils.*;
import gitlet.stage_object.*;

import java.io.File;
import java.util.Date;

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
    public void add(){

        File file_tmp = join(STAGE_DIR, "stage_object");
        String file_content = readContentsAsString(obj.file_ref);
        String file_hash = Utils.sha1(file_content);
        if(STAGE_DIR.mkdir()){
            obj.set_file_hash(file_hash);
            Utils.writeObject(file_tmp, obj);
            Utils.writeContents(join(STAGE_DIR, file_hash), file_content);
        }else{
            // current version is the same with the one in the stage, don't add it
            stage_object obj_tmp = Utils.readObject(file_tmp, stage_object.class);
            if (!(obj_tmp.file_hash.equals(file_hash))){
                join(STAGE_DIR, obj_tmp.file_hash).delete();
                obj.set_file_hash(file_hash);
                Utils.writeObject(file_tmp, obj);
                Utils.writeContents(join(STAGE_DIR, file_hash), file_content);
                System.out.println("not equal");
            }
        }

    }
}
