package cn.javass.chapter1.javabean;

public class UserBean implements java.io.Serializable {

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 因为我们只关注表现层，此处只是模拟，实际项目需要改掉！
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public boolean login() {
		if("zhang".equals(this.username) && "123".equals(this.password)) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserBean [username=" + username + ", password=" + password
				+ "]";
	}
	
	
}
