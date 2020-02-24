<?php 

$file_path = "files/G24/1215133127/accept/";
$response = array();
// $group  = "G" . $_POST['group_id'] . "/";
// $asu_id = $_POST['id'] . "/";
// $accept = $_POST['accept'];
//
// if($accept == 1){
//     $file_path = $file_path . $group . $asu_id . "reject/";
// } else {
//     $file_path = $file_path . $group . $asu_id . "accept/";
// }

// if(!file_exists($file_path)){
//     mkdir($file_path, 0777, true);
// }else{
//     chmod($file_path, 0777);
// }
chmod($file_path, 0777);
$file = $file_path . basename($_FILES["file"]["name"]);
if (move_uploaded_file($_FILES["file"]["tmp_name"], $file)) {
    $success = true;
    $message = "Successfully uploaded";
//     print_r($_FILES);
} else {
    $success = false;
    $message = "Error while uploading";
//     print_r($_FILES);
}

$response["success"] =  $success;
$response["message"] = $message;
// print_r($response)
header('Content-type:application/json;charset=utf-8');
echo json_encode($response);

?>