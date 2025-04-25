package emcapi.bxlib.exception;

/**
 *  @Author Biggest_Xuan
 *  2023/01/07
 */

public class CheatException extends RuntimeException {
    public CheatException(String id){
        super("Why you install " + id + "! (ノ=Д=)ノ┻━┻");
    }
}
