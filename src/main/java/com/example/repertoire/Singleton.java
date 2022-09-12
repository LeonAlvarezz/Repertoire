package com.example.repertoire;

public class Singleton {
    private static final Singleton instance = new Singleton();
    private String username;
    private String dataBaseName;
    private Singleton(){}
    public static Singleton getInstance()
    {
            return instance;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getDatabaseName()
    {
        return dataBaseName;
    }
    public void setDatabaseName(String dataBaseName)
    {
        this.dataBaseName = dataBaseName;
    }


}
