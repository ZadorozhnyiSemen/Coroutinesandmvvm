//
// Created by zador on 08.05.2019.
//

#include <jni.h>

extern "C" {

    JNIEXPORT jstring JNICALL
    Java_com_simon_pattern_views_main_MainActivity_getClientId(JNIEnv *env, jobject instance) {
        return env->NewStringUTF("NGI0YzI0MWI2OTcxNDk5NzhiMjgyNDQ4MGI2YWVjN2M=");
    }

}
