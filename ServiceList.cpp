//
// Created by ehsan on 12/6/2017.
//
#include <iostream>
#include "ServiceList.h"

using namespace std;

ServiceList::ServiceList() {
    head = NULL;
}

void ServiceList::addService(struct node **root, struct node *service) {
    if (*root == NULL) {
        *root = service;
    } else {
        struct node *p = *root;
        for (; p->next; p = p->next);
        p->next = service;
    }
}

void ServiceList::addService(string serviceName) {
    struct node *service = new node;
    service->name = serviceName;
    service->tag = 0;
    service->next = NULL;
    service->innerList = NULL;
    addService(&head, service);
}

void ServiceList::addSubservice(std::string subServiceName, std::string serviceName) {
    struct node *service = new node;
    service->name = subServiceName;
    service->tag = 0;
    service->next = NULL;
    service->innerList = NULL;
    for (struct node *p = head; p->next; p = p->next) {
        if (p->name == serviceName) {
            p->tag = 1;
            addService(&p->innerList, service);
            return;
        }
    }
}

void ServiceList::printAll() {
    printAll(head);
}

void ServiceList::printAll(struct node *head) {
    struct node *p;
    for (p = head; p; p = p->next) {
        if (p->tag == 0)
            cout << p->name << "," << p->tag << " ";
        else {
            printAll(p->innerList);
        }

    }

}



