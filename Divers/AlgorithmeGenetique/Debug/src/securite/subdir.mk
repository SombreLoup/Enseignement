################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/securite/ContreMesure.cpp 

OBJS += \
./src/securite/ContreMesure.o 

CPP_DEPS += \
./src/securite/ContreMesure.d 


# Each subdirectory must supply rules for building sources it contributes
src/securite/%.o: ../src/securite/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


