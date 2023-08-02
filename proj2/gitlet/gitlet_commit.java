package gitlet;

import java.io.File;
import java.util.Date;
import java.util.List;

import static gitlet.Utils.join;
import static gitlet.Utils.readContentsAsString;
import gitlet.commit_object;
public class gitlet_commit {
    private stage_object obj;
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGE_DIR = join(GITLET_DIR, ".stage");

    public void update_by_stage(String msg){
        List<String> stage_obj_path_lst = Utils.plainFilenamesIn(STAGE_DIR);
        String parent_hash = Utils.readContentsAsString(join(GITLET_DIR,"header"));
        commit_object parent_commit = Utils.readObject(join(GITLET_DIR, parent_hash), commit_object.class);

        boolean update_state = false;
        for (String stage_obj_path : stage_obj_path_lst){
            stage_object stage_obj = Utils.readObject(join(STAGE_DIR,stage_obj_path ), stage_object.class);
            if (stage_obj.remove){
                join(STAGE_DIR, ".file", stage_obj.file_hash).delete();
                parent_commit.file_ref_dict.remove(stage_obj.file_name);
                parent_commit.parent_ref = parent_hash;
                parent_commit.message = msg;
                parent_commit.timestamp = new Date();
                update_state = true;
            }else{
                if (parent_commit.update_by_stage(stage_obj)){
                    parent_commit.parent_ref = parent_hash;
                    parent_commit.message = msg;
                    parent_commit.timestamp = new Date();
                    update_state = true;
                }
            }
        }
        if(update_state){
            Commit.dump_header(Commit.dump_commit(parent_commit));
        }
        commit_object.destroy_stage();
    }
}
