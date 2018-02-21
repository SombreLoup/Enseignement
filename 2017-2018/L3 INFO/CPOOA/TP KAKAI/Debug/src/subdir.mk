################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Element.cpp \
../src/Main.cpp \
../src/Meuble.cpp \
../src/Option.cpp \
../src/Poignee.cpp 

OBJS += \
./src/Element.o \
./src/Main.o \
./src/Meuble.o \
./src/Option.o \
./src/Poignee.o 

CPP_DEPS += \
./src/Element.d \
./src/Main.d \
./src/Meuble.d \
./src/Option.d \
./src/Poignee.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


