<?php
    include_once 'database.php';

    $db = new Database();
    
    $account_id = ""; $name="";$password="";$email="";
    
    if ($_SERVER['REQUEST_METHOD'] == "GET"){
        if (isset($_GET['account_id'])){
            $account_id = $_GET['account_id'];
        }
        if (isset($_GET['name'])){
            $name = $_GET['name'];
        }
        if (isset($_GET['password'])){
            $password = $_GET['password'];
        }
        if (isset($_GET['email'])){
            $email = $_GET['email'];
        }
        
        if ($account_id == "" || $name=="" || $password=="" || $email==""){
             echo "0";
        } else {
            $update_account = "update account set name='$name', password='$password',email='$email' where id = $account_id";
        
            $result = $db->update($update_account);
            if ($result){
                echo "1";
            } else {
                echo "0";
            }
        }
        
       
        
    }
    

?>