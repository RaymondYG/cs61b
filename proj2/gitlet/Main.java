package gitlet;
import gitlet.stage_object.*;
import gitlet.gitlet_add.*;
import gitlet.commit_object.*;
import gitlet.Commit.*;
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
                commit_object tmp_commit = new commit_object(parent_commit.timestamp,
                        parent_commit.message,
                        parent_commit.parent_ref,
                        parent_commit.file_ref_dict);
                if (tmp_commit.update_by_stage(obj_tmp)){
                    Commit.dump_header(Commit.dump_commit(tmp_commit));
                }
        }
    }
    public static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }

}
