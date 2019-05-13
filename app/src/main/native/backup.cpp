//
// Created by zador on 10.05.2019.
//

#include <jni.h>

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_simon_pattern_repository_SpotifyRepository_loadClientId2(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("ZmM3ZjhkYTIxY2Q0NDVlZmIzZmQ0MTJjODg3YzRhZTY=");
}
}