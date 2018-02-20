################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/AG/Traceur/Traceur.cpp \
../src/AG/Traceur/TraceurCSV.cpp \
../src/AG/Traceur/TraceurGeneration.cpp 

OBJS += \
./src/AG/Traceur/Traceur.o \
./src/AG/Traceur/TraceurCSV.o \
./src/AG/Traceur/TraceurGeneration.o 

CPP_DEPS += \
./src/AG/Traceur/Traceur.d \
./src/AG/Traceur/TraceurCSV.d \
./src/AG/Traceur/TraceurGeneration.d 


# Each subdirectory must supply rules for building sources it contributes
src/AG/Traceur/%.o: ../src/AG/Traceur/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


