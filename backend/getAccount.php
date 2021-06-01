            <?php
                include_once "database.php";
                
                $db = new Database();
                
                $select_account = "select * from account";
                $result = $db->select($select_account);
                if ($result != false){
                    $i = 1;
                    while($row = $result->fetch_assoc()){
                        echo "<tr>";
                        echo "<td>" . $i . "</td>";
                        echo "<td>" . $row['name'] . "</td>";
                        echo "<td>" . $row['user'] . "</td>";
                        echo "<td>" . $row['password'] . "</td>";
                        echo "<td>" . $row['email'] . "</td>";
                        echo "</tr>";
                        $i++;
                    }
                }
            ?>