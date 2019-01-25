# EasyDatabase

public class SQLDB {
    public static void main(String[] args) {
       
       EasyDatabase sql = new EasyDatabase(
               EasyDatabase.ACCESS,  //Database Server
               "D:\\Backup\\Database.accdb", //Host or File
               null, //Database
               null, //Username
               null);  //Password
       
       DatabaseListener dbListener = new DatabaseListener(){
            @Override
            public void onSuccess(DatabaseEvent e) {
                System.out.println(e);
            }

            @Override
            public void onFailed(DatabaseEvent e) {
                System.out.println(e);
            }           
        };
       sql.setDatabaseListener(dbListener);
       
       Values values = new Values();     
            values.put("id", 1 , Values.INT);
       
        
       InsertQuery query = InsertQuery.into("emp").values(values);
       
       sql.insert(query);
       
   
       //Insert Demo
        Values values = new Values();
        values.put("name", "Piyush Patil", Values.STRING);
        values.put("id", "11", Values.INT);
        values.put("salary", 378000.80, Values.DOUBLE);
          
        
        
        
        InsertQuery insertQuery = InsertQuery.into("emp").values(values);
        sql.insert(insertQuery);
        
        //Select Demo
        SelectQuery select = SelectQuery.select()
                .fromTable("emp")
                .where(SelectQuery.getCondition()
                    .like("name", "%patil%")
                    .AND()
                    .smallerThan("salary",200000, ColumnType.DOUBLE)
                );
            
        
        ResultSet rs = sql.select(select);
        
        try{
            while(rs.next()){
                System.out.println(rs.getString(2));
            }    
        }catch(Exception e){e.printStackTrace();}
    
        
        //Delete Demo
        DeleteQuery delete = DeleteQuery.fromTable("emp")
                .where(DeleteQuery.getCondition()
                    .equalTo("name", "Prem Patil", ColumnType.STRING)
                );
        
        //sql.delete(delete);
 
    }
}
