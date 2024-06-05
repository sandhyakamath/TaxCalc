#include <iostream>
#include "com_godel_engine_JNI_SeniorCitizenFemaleJNICaller.h"
#include "../command/SeniorCitizenFemale.cpp"


JNIEXPORT jdouble JNICALL Java_com_godel_engine_JNI_SeniorCitizenFemaleJNICaller_computeTax
  (JNIEnv *env, jobject thisObject, jobject userData){
          jclass userDataClass = env->GetObjectClass(userData);
          jmethodID methodId = env->GetMethodID(userDataClass, "getBasic", "()D");
          jdouble basic = (jdouble)env->CallDoubleMethod(userData, methodId);

          methodId = env->GetMethodID(userDataClass, "getAllowance", "()D");
          jdouble allowance = (jdouble)env->CallDoubleMethod(userData, methodId);

           methodId = env->GetMethodID(userDataClass, "getDA", "()D");
          jdouble da = (jdouble)env->CallDoubleMethod(userData, methodId);

           methodId = env->GetMethodID(userDataClass, "getHRA", "()D");
          jdouble hra = (jdouble)env->CallDoubleMethod(userData, methodId);

           methodId = env->GetMethodID(userDataClass, "getCESS", "()D");
          jdouble cess = (jdouble)env->CallDoubleMethod(userData, methodId);

           methodId = env->GetMethodID(userDataClass, "getDeduction", "()D");
          jdouble deduction = (jdouble)env->CallDoubleMethod(userData, methodId);

           methodId = env->GetMethodID(userDataClass, "getSurcharge", "()D");
          jdouble surcharge = (jdouble)env->CallDoubleMethod(userData, methodId);

          SeniorCitizenFemale seniorCitizenFemale;
          return seniorCitizenFemale.compute(basic, allowance, da, hra, cess, deduction, surcharge);
  }