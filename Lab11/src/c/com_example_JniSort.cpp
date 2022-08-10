#include "com_example_JniSort.h"
#include <vector>
#include <algorithm>
#include <functional>
#include <iostream>
using namespace std;


vector<jdouble> toNativeArray(JNIEnv *env, jobjectArray &arr) {
    long N = env->GetArrayLength(arr);
    vector<jdouble> array(N);

    jclass doubleClass = env->FindClass("java/lang/Double");
    jmethodID getDoubleValue = env->GetMethodID(doubleClass, "doubleValue", "()D");
    for (jsize i = 0; i < N; i++) {
        jobject doubleObject = env->GetObjectArrayElement(arr, i);
        array[i] = env->CallDoubleMethod(doubleObject, getDoubleValue);
        env->DeleteLocalRef(doubleObject);
    }
    return array;
}

jobjectArray fromNativeArray(JNIEnv *env, vector<jdouble> &arr) {
    long N = arr.size();
    jclass doubleClass = env->FindClass("java/lang/Double");
    jmethodID doubleConstructor = env->GetMethodID(doubleClass, "<init>", "(D)V");
    auto resultArray = env->NewObjectArray(N, doubleClass, NULL);
    for(jsize i = 0; i < N; i++){
        jobject element = env->NewObject(doubleClass, doubleConstructor, arr[i]);
        env->SetObjectArrayElement(resultArray, i, element);
        env->DeleteLocalRef(element);
    }
    return resultArray;
}

JNIEXPORT jobjectArray JNICALL Java_com_example_JniSort_sort01
(JNIEnv *env, jobject obj, jobjectArray arr, jboolean order) {
    bool boolOrder = order;
    vector<jdouble> array = toNativeArray(env, arr);

	if (boolOrder)
		sort(array.begin(), array.end());
	else
		sort(array.begin(), array.end(), greater <>());

   jobjectArray resultArray = fromNativeArray(env, array);

   return resultArray;
}


JNIEXPORT jobjectArray JNICALL Java_com_example_JniSort_sort02
(JNIEnv* env, jobject obj, jobjectArray tab) {


	jclass sortClass = env->GetObjectClass(obj);
    jclass booleanClass = env->FindClass("java/lang/Boolean");
    jmethodID getBooleanValue = env->GetMethodID(booleanClass, "booleanValue", "()Z");
    jboolean boolOrder = env->CallBooleanMethod(obj, getBooleanValue);

    vector<jdouble> array = toNativeArray(env, tab);

	if (boolOrder)
		sort(array.begin(), array.end());
	else
		sort(array.begin(), array.end(), greater <>());

    for(jdouble a : array){
        cout << "elem " <<  a << endl;
    }

    jobjectArray resultArray = fromNativeArray(env, array);

    return resultArray;
}


JNIEXPORT void JNICALL Java_com_example_JniSort_sort03
(JNIEnv* env, jobject obj) {


}
