#!/usr/bin/env bash

versionName=`grep 'versionName' ../build.gradle | sed 's/ //g' | cut -d \=  -f 2 | sed 's/\"//g'`
echo "versionName="$versionName
versionCode=`grep 'versionCode' ../build.gradle | sed 's/ //g' | cut -d \=  -f 2`
echo "versionCode="$versionCode
fileApkName="zyp_v${versionName}_zyp_release.apk"
apkPath="$(pwd)/build/outputs/apk/${fileApkName}"
echo "apkPath=${apkPath}"
echo "开始上传${apkPath}到蒲公英..."
echo "执行curl -F file=@${apkPath} -F uKey=1549f3fe13004de0e3ad36b5e17d8c91f -F _api_key=1fbae3b05d6f0738ff7a7c01f9ad3d66c http://www.pgyer.com/apiv1/app/upload"
curl -F "file=@${apkPath}" -F "uKey=1549f3fe13004de0e3ad36b5e17d8c91f" -F "_api_key=1fbae3b05d6f0738ff7a7c01f9ad3d66c" http://www.pgyer.com/apiv1/app/upload
echo "上传完成"