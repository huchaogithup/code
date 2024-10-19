#include <org_example_Jni.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>


void testMalloc(jint i){
        int *arr  =(int*)malloc(sizeof(int)*i);
        for(int j=0; j< i;++j){
            arr[j]=1;
        }
}


void Java_org_example_Jni_helloworld
  (JNIEnv *, jobject, jint i){
            testMalloc(i);

        printf("helloworld\r\n");
        printf("%d\r\n",getpid());
        fflush(stdout);
  }