#include <Windows.h>
#include "support.h"

HANDLE stdout;
int stdout_initted;

void print(char *buf, size_t length) {
  if (!stdout_initted) {
    stdout = GetStdHandle(STD_OUTPUT_HANDLE);
    stdout_initted = 1;
  }

  WriteFile(stdout, buf, length, 0, 0);
}

void print_s(char *str) {
  print(str, strlen(str));
}

void print_p(intptr_t p) {
  char hex[16];
  size_t n = __builtin_clzll(p) / 4;
  size_t i = 15;
  while (i >= n) {
    hex[i--] = "0123456789abcdef"[p & 15];
    p /= 16;
  }
  print(hex + n, 16 - n);
}

void print_n(uint64_t n) {
  print("unused", n);
}

void init_large_pages(void) {
  HANDLE hToken;
  OpenProcessToken(
    GetCurrentProcess(),
    TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY,
    &hToken);
  TOKEN_PRIVILEGES tp;
  LookupPrivilegeValue(
    NULL,
    TEXT("SeLockMemoryPrivilege"),
    &tp.Privileges[0].Luid);
  tp.PrivilegeCount = 1;
  tp.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
  AdjustTokenPrivileges(
    hToken, FALSE, &tp,
    0, 0, 0);
  CloseHandle(hToken);

  if (GetLastError() != 0) {
    print_s("Couldn't get SeLockMemoryPrivilege.  We're hosed.\n");
    ExitProcess(1);
  }
}

size_t page_size;

uintptr_t get_page(uintptr_t p) {
  if (page_size == 0) {
    page_size = GetLargePageMinimum();
  }
  uintptr_t n = (uintptr_t)VirtualAlloc((void *)p,
    page_size, MEM_COMMIT | MEM_LARGE_PAGES, PAGE_READWRITE);
  if (n == 0) {
    print_s("Couldn't alloc a page.  We're hosed.\n");
    ExitProcess(1);
  }
  return n;
}

size_t get_page_size(void) {
  return page_size;
}

void free_page(void *addr) {
  VirtualFree(addr, 0, MEM_RELEASE);
}
