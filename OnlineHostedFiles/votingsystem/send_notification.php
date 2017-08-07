<?php
require_once 'Firebase.php';
require_once 'DbOperation.php';

//$devicetoken = array();
//$token['token'] = //'cj1_Xi8yOvw:APA91bFFhmZCXvmP2hXArC40apSp2FKBPTScITbgQMg_yLCQLhGfbbyosw_PGnMEpS8INQBMRu3IUm6r4k1lz2MDDAV1-EQ9utnuH0d1e8erLTurLMK3TwK8YnNgo_b04XD4oiImNb_A';
//array_push($devicetoken, $token['token']);


$firebase = new Firebase(); 
$res = array();
$res['data']['title'] = "Hello";
$res['data']['message'] = "Test";
//$res['data']['image'] = $this->image;
$db = new DbOperation();
$devicetoken = $db->getAllTokens();

echo $firebase->send($devicetoken, $res);
?>


