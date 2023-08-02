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
                gitlet_commit tmp_commit = new gitlet_commit();
                tmp_commit.update_by_stage(args[1]);
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
                validateNumArgs("global-log", args,1);
                gitlet_log.global_log();
                break;
            case "find":
                validateNumArgs("find", args,2);
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
