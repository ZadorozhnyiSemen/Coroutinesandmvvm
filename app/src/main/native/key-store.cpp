//
// Created by zador on 08.05.2019.
//

#include <jni.h>

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_simon_pattern_repository_SpotifyRepository_loadClientId(JNIEnv *env, jobject instance) {
        return env->NewStringUTF("NGI0YzI0MWI2OTcxNDk5NzhiMjgyNDQ4MGI2YWVjN2M=");
    }

JNIEXPORT jstring JNICALL
Java_com_simon_pattern_repository_SpotifyRepository_loadRedirectUrl(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("aHR0cDovL3JlcGxheS1hcHAtbG9naW4vY2FsbGJhY2s=");
}
}
