package yuan.web.exception;


import yuan.web.base.StatusCode;

/**
 * PFT通用型异常
 * @author Administrator
 *
 */
public class PFTException extends RuntimeException {

	private static final long serialVersionUID = -1977245837696067325L;

	private String statusCode;
	
    public PFTException(String message) {
    	super(message);
    }
    
    public PFTException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public PFTException(String statusCode, String message) {
    	super(message);
    	this.statusCode=statusCode;
    }
    
    public PFTException(String statusCode, String message, Throwable cause) {
    	super(message,cause);
    	this.statusCode=statusCode;
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public static PFTException build(String statusCode){
		return new PFTException(statusCode, StatusCode.getErrorMessage(statusCode));
	}
	
}
