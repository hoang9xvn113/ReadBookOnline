<?php
    include_once "config.php";

    class Database{

        public $link;
        public $error;

        function __construct()
        {
            $this->link = new mysqli(DB_SERVER,DB_USER,DB_PASS,DB_NAME);
        }

        function insert($insert){
            return $this->link->query($insert);
        }

        function update($update){
            return $this->link->query($update);
        }

        function delete($delete){
            return $this->link->query($delete);
        }

        function select($select){
            $result = $this->link->query($select);
            if ($result == false){
                return false;
            } else {
                if ($result->num_rows > 0)  return $result;
            }
            return false;
        }

    }

?>