#include "status.h"
#include "constants.h"

Status::Status(Terminal* terminal):terminal(terminal){
	this->nextPrint = 0;
	this->distMm = -1;
	this->weightGr = -1;
}

void Status::run(unsigned long nowMs){
	if(nowMs>this->nextPrint){
		this->terminal->writeStatus(this->distMm, this->weightGr);
		this->nextPrint = nowMs + STATUS_DELAY_MS;
	}
}

void Status::setDistMm(int value){
	this->distMm = value;
}

void Status::setWeightGr(int value){
	this->weightGr = value;
}
