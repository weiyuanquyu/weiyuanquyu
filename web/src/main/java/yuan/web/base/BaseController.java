package yuan.web.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import yuan.web.exception.PFTException;


public class BaseController {


    private static Logger logger = LoggerFactory
            .getLogger(BaseController.class);

    /**
     * 处理service层抛出的异常
     *
     * @param
     */
    @ExceptionHandler(PFTException.class)
    @ResponseBody
    public WebStoreBean handlerServiceException(
            PFTException pftException) {
        logger.error("发生了{}类型的异常，异常信息为：{}",
                pftException.getClass(),
                pftException.getMessage());
        return WebStoreBean.fail(pftException.getStatusCode());
    }

    /**
     * 处理未知异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebStoreBean handlerException(Exception exception) {
        logger.error("发生了{}类型的异常，异常信息为：{}", exception.getClass(),
                exception.getMessage());
        logger.error("堆栈",exception);
        return WebStoreBean.unknown(exception.getMessage());
    }

    public String escapeURI(String s){
        try {
            return java.net.URLDecoder.decode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}

