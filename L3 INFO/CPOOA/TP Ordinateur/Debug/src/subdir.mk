################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Composant.cpp \
../src/DisqueDur.cpp \
../src/Ecran.cpp \
../src/Main.cpp \
../src/Ordinateur.cpp 

OBJS += \
./src/Composant.o \
./src/DisqueDur.o \
./src/Ecran.o \
./src/Main.o \
./src/Ordinateur.o 

CPP_DEPS += \
./src/Composant.d \
./src/DisqueDur.d \
./src/Ecran.d \
./src/Main.d \
./src/Ordinateur.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


