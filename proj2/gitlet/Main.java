package gitlet;
import gitlet.stage_object.*;
import gitlet.gitlet_add.*;
import gitlet.commit_object.*;
import gitlet.Commit.*;
import gitlet.gitlet_rm.*;
import gitlet.gitlet_log.*;
import java.io.File;
import java.util.Date;

import static gitlet.Utils.join;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0){
            System.out.println("Please enter a command.");
            System.exit(0);
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                validateNumArgs("init", args, 1);
                Repository.init();
                commit_object tmp_init = new commit_object(new Date(0),
                                                            "initial commit",
                                                            null,
                                                            null);
                Commit.dump_header(Commit.dump_commit(tmp_init));
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                validateNumArgs("add", args, 2);
                gitlet_add tmp_add = new gitlet_add(new Date(),
                                            join(CWD, args[1]),
                                            args[1]);
                tmp_add.add();
                break;
            case "commit":
                if (args.length != 2){
                    System.out.println("Please enter a commit message.");
                    System.exit(0);
                }
                if (!join(new File(System.getProperty("user.dir")), ".gitlet", ".stage").exists()){
                    System.out.println("No changes added to the commit.");
                    System.exit(0);
                }
                stage_object obj_tmp = Utils.readObject(join(System.getProperty("user.dir"),
                                        ".gitlet",
                                                ".stage",
                                                "stage_object"),
                                                stage_object.class);

                String parent_hash = Utils.readContentsAsString(join(new File(System.getProperty("user.dir")), ".gitlet","header"));
                commit_object parent_commit = Utils.readObject(join(System.getProperty("user.dir"),
                                ".gitlet",
                                parent_hash),
                                commit_object.class);
                if (obj_tmp.remove){
                    parent_commit.file_ref_dict.remove(obj_tmp.file_name);
                    parent_commit.parent_ref = parent_hash;
                    parent_commit.message = args[1];
                    parent_commit.timestamp = new Date();
                    Commit.dump_header(Commit.dump_commit(parent_commit));
                    commit_object.destroy_stage();

                }else{
                    commit_object tmp_commit = new commit_object(parent_commit.timestamp,
                            parent_commit.message,
                            parent_commit.parent_ref,
                            parent_commit.file_ref_dict);
                    if (tmp_commit.update_by_stage(obj_tmp)){
                        tmp_commit.parent_ref = parent_hash;
                        tmp_commit.message = args[1];
                        Commit.dump_header(Commit.dump_commit(tmp_commit));
                    }
                }
                break;
            case "rm":
                validateNumArgs("rm", args, 2);
                gitlet_rm tmp_rm = new gitlet_rm();
                tmp_rm.rm(args[1]);
                break;
            case "log":
                validateNumArgs("rm", args, 1);
                gitlet_log.log();
                break;
            case "global-log":
                validateNumArgs("global-log", 1);
                gitlet_log.global_log();
                break;
            case "find":
                validateNumArgs("find", 2);
                gitlet_log.find(args[1]);
        }
    }
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }

}
