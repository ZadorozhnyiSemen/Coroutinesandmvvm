//
// Created by zador on 08.05.2019.
//

#include <jni.h>

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_simon_pattern_MainActivity_getAPIKey(JNIEnv *env, jobject instance) {
        return env-> NewStringUTF("c2VjcmV0X2tleQ==");
    }

}
