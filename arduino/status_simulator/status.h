#ifndef STATUS_H
#define STATUS_H

#include "terminal.h"

class Status {
public:
	Status(Terminal *terminal);

	void setDistMm(int value);
	void setWeightGr(int value);

	void run(unsigned long nowMs);

private:
	Terminal *terminal;
	unsigned long nextPrint;

	int distMm;
	int weightGr;

};

#endif
