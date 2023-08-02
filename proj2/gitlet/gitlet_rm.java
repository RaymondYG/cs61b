package gitlet;
import java.io.File;
import java.util.Date;

import gitlet.commit_object.*;
import gitlet.Utils.*;
import static gitlet.Utils.join;

public class gitlet_rm {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGE_DIR = join(GITLET_DIR, ".stage");
    public boolean stage_rm(String file){
        File file_path = Utils.join(CWD, file);
        if(!STAGE_DIR.exists() || !(Utils.join(STAGE_DIR,".file",Utils.sha1(Utils.readContentsAsString(file_path))).exists())){
            return false;
        }
        return true;
    }
    public void error(){
        System.out.println("No reason to remove the file.");
        System.exit(0);
    }
    public boolean commit_rm(String file){
        File parent_path = join(new File(System.getProperty("user.dir")), ".gitlet","header");
        if (!parent_path.exists()){
            this.error();
            return false;
        }
        String parent_hash = Utils.readContentsAsString(parent_path);
        commit_object parent_commit = Utils.readObject(join(System.getProperty("user.dir"),
                        ".gitlet",
                        parent_hash),
                        commit_object.class);
        if (parent_commit.file_ref_dict == null || (!parent_commit.file_ref_dict.containsKey(file))){
            this.error();
            return false;
        }
        else{
            gitlet_add tmp_add = new gitlet_add(new Date(),
                    join(CWD,file),
                    file);

            tmp_add.set_remove();
            tmp_add.add();
        }
        return true;
    }
    public void rm(String file){
        if(!stage_rm(file)){
            if (!commit_rm(file)){
                error();
            }
        }
    }
}
