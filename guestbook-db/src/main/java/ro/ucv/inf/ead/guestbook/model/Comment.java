package ro.ucv.inf.ead.guestbook.model;

import java.util.Date;

public class Comment {
	private long id;
	private String userName;
	private String userEmail;
	private Date date;
	private String message;
	
	public Comment() {

	}

	public Comment(long id, String userName, String userEmail, Date date, String message) {
		this.id = id;
		this.userName = userName;
		this.userEmail = userEmail;
		this.date = date;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString(){
	  StringBuilder sb = new StringBuilder();
	  sb.append("Comment(");
	  sb.append("id:").append(id);
	  sb.append(", ");
	  sb.append("date:").append(date);
	  sb.append(", ");
	  sb.append("userName:").append(userName);
	  sb.append(", ");
	  sb.append("message:").append(message);
	  sb.append(")");
	  return sb.toString();
	  
	}

}
