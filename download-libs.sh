BASE_URL=https://dl.cryptlex.com/downloads
VERSION="v3.14.9";

mkdir -p tmp/windows
curl -O ${BASE_URL}/${VERSION}/LexActivator-Win.zip
unzip LexActivator-Win.zip -d ./tmp/windows
cp ./tmp/windows/libs/vc14/x64/LexActivator.dll src/main/resources/win32-x86-64/
cp ./tmp/windows/libs/vc14/x86/LexActivator.dll src/main/resources/win32-x86/

mkdir -p tmp/macos
curl -O ${BASE_URL}/${VERSION}/LexActivator-Mac.zip
unzip LexActivator-Mac.zip -d ./tmp/macos
cp ./tmp/macos/libs/clang/x86_64/libLexActivator.dylib src/main/resources/darwin-x86-64/
cp ./tmp/macos/libs/clang/arm64/libLexActivator.dylib src/main/resources/darwin-aarch64/

mkdir -p tmp/linux
curl -O ${BASE_URL}/${VERSION}/LexActivator-Linux.zip
unzip LexActivator-Linux.zip -d ./tmp/linux
cp ./tmp/linux/libs/gcc/amd64/libLexActivator.so src/main/resources/linux-x86-64/
cp ./tmp/linux/libs/gcc/i386/libLexActivator.so src/main/resources/linux-x86/
cp ./tmp/linux/libs/gcc/arm64/libLexActivator.so src/main/resources/linux-aarch64/

rm -f LexActivator-Win.zip
rm -f LexActivator-Mac.zip
rm -f LexActivator-Linux.zip
rm -R -f ./tmp
