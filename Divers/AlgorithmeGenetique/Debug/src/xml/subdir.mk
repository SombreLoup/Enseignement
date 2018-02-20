################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/xml/Parser.cpp \
../src/xml/tinyxml.cpp \
../src/xml/tinyxmlerror.cpp \
../src/xml/tinyxmlparser.cpp 

OBJS += \
./src/xml/Parser.o \
./src/xml/tinyxml.o \
./src/xml/tinyxmlerror.o \
./src/xml/tinyxmlparser.o 

CPP_DEPS += \
./src/xml/Parser.d \
./src/xml/tinyxml.d \
./src/xml/tinyxmlerror.d \
./src/xml/tinyxmlparser.d 


# Each subdirectory must supply rules for building sources it contributes
src/xml/%.o: ../src/xml/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


