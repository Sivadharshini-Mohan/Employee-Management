public class BaseDao {

    Connection connection = DriveManger.getConnection(Constants.DRIVE_URL,Constant.SQL_USER_ID,Constant.SQL_PASSWORD);
    Statement statement = 
}