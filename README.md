# Easy Database (API Demo)

**Initialization of Database**
```
EasyDatabase sql = new EasyDatabase(
           EasyDatabase.MYSQL,  //Database Server
           "DATABASE_URL", //Host or File
           "DATABASE_NAME", //Database
           "USERNAME", //Username
           "PASSWORD");  //Password
```

**Database Driver Constants**
```
EasyDatabase.MYSQL  //For MySQL Database
EasyDatabase.ORACLE //For Oracle Database
EasyDatabase.ACCESS //For MS-Access Database
```

**Register Listener for Database events**

```
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
```

**Insertion**

```
Values values = new Values();
values.put("name", "Piyush Patil", Values.STRING);
values.put("id", "11", Values.INT);
values.put("salary", 378000.80, Values.DOUBLE);

InsertQuery insertQuery = InsertQuery.into("emp").values(values);
sql.insert(insertQuery);
```

**Select from Table**

```
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
```

**Deletion of Record**

```
DeleteQuery delete = DeleteQuery.fromTable("emp")
            .where(DeleteQuery.getCondition()
                .equalTo("name", "Prem Patil", ColumnType.STRING)
            );
    
sql.delete(delete);

```
