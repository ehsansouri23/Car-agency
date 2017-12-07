//
// Created by ehsan on 12/6/2017.
//

#ifndef DS_SERVICELIST_H
#define DS_SERVICELIST_H

#include <string>

class ServiceList {
private:
    struct node {
        std::string name;
        int model;
        std::string userDescription;
        std::string agencyDescription;
        int costs;
        int tag; // 0 for data. 1 for an inner list
        int numOfAgencies;
        struct node *innerList;
        struct node *next;
    };

public:
    struct node *head;

    struct node *curr;

    ServiceList();

    void addService(std::string serviceName);

    void addService(struct node **root, struct node *service);

    void addSubservice(std::string subServiceName, std::string serviceName);

    void addOffer(std::string serviceName, std::string agencyName);

    void deleteService(std::string serviceName, std::string agencyName);

    void printAll();

    void printAll(struct node *head);
};

#endif //DS_SERVICELIST_H
