LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := processor
LOCAL_SRC_FILES := processor.cpp

include $(BUILD_SHARED_LIBRARY)