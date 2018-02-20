################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Application.cpp \
../src/Couleur.cpp \
../src/Materiau.cpp \
../src/Matrices4D.cpp \
../src/Points3D.cpp \
../src/Source.cpp \
../src/Spheres.cpp \
../src/Vecteurs3D.cpp 

OBJS += \
./src/Application.o \
./src/Couleur.o \
./src/Materiau.o \
./src/Matrices4D.o \
./src/Points3D.o \
./src/Source.o \
./src/Spheres.o \
./src/Vecteurs3D.o 

CPP_DEPS += \
./src/Application.d \
./src/Couleur.d \
./src/Materiau.d \
./src/Matrices4D.d \
./src/Points3D.d \
./src/Source.d \
./src/Spheres.d \
./src/Vecteurs3D.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


