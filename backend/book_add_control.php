            <?php
                include_once "database.php";
            
                $db = new Database();
            
                $name = $author = $category = $des = "";
            
                if ($_SERVER['REQUEST_METHOD'] == "POST"){
                    if (isset($_POST['name'])){
                        $name = $_POST['name'];
                    }
                    if (isset($_POST['author'])){
                        $author = $_POST['author'];
                    }
                    if (isset($_POST['category'])){
                        $category = $_POST['category'];
                    }
                    if (isset($_POST['des'])){
                        $des = $_POST['des'];
                    }
                    if ($name=="" || $author=="" || $category=="" || $des==""){
                        echo "Vui lòng nhập đầy đủ dữ liệu";
                    } else {
                        $select_img = "select max(img) as img from book";
                        $img = $db->select($select_img)->fetch_assoc()['img'] + 1;
                        $insert_book = "insert into book(name,author,category_id,description,img) values('$name','$author',$category,'$des',$img)";
                        $result_insert = $db->insert($insert_book);
                        if ($result_insert) {
                            echo "Thêm thành công";
                        } else {
                            echo "Tên sách bị trùng vui lòng nhập lại";
                        }
                    }
                }
            ?>