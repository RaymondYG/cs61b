package gitlet;
import gitlet.Utils.*;
import gitlet.stage_object.*;

import java.io.File;
import java.util.Date;

import static gitlet.Utils.join;

public class gitlet_add {
    private stage_object obj;
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGE_DIR = join(GITLET_DIR, ".stage");
    public gitlet_add(Date input_timestamp, String input_message, File input_file_ref){
        if(!input_file_ref.exists()){
            System.out.println("File does not exist.");
            System.exit(0);
        }
        obj = new stage_object(input_timestamp, input_message,input_file_ref);
        if(STAGE_DIR.mkdir()){
            File file_tmp = join(STAGE_DIR, "stage_object");
            obj.set_file_blobs(Utils.readContentsAsString(obj.file_ref));
            Utils.writeObject(file_tmp, obj);
        }else{
            // current version is the same with the one in the stage, don't add it
            File file_tmp = join(STAGE_DIR, "stage_object");
            stage_object obj_tmp = Utils.readObject(file_tmp, stage_object.class);
            obj.set_file_blobs(Utils.readContentsAsString(obj.file_ref));
            if (!(Utils.sha1(obj.file_blobs)==Utils.sha1(obj_tmp.file_blobs))){

            }
        }

    }
}
