/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author linhphan
 */
public class ArrayListFileItem{
    
    private ArrayList<FileItem> list;
    
    public ArrayListFileItem(ArrayList<FileItem> list){
        this.list = list;
    }
    
    public FileItem getFile(String name){
        for(FileItem fileItem: list){
            if((fileItem.isFormField() == false) && fileItem.getFieldName().equals(name)){
                return fileItem;
            }
        }
        return null;
    }
    
    public String getValue(String name){
        for(FileItem fileItem: list){
            if((fileItem.isFormField() == true) && fileItem.getFieldName().equals(name)){
                return fileItem.getString();
            }
        }
        return null;
    }
    
}
