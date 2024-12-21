'use client';

import { NextUIProvider } from '@nextui-org/react';
import { useRouter } from 'next/navigation';
import React, { useEffect } from 'react';
import { Toaster } from 'react-hot-toast';
import Cookies from 'js-cookie';

const Providers = ({ children }: { children: React.ReactNode }) => {
  const router = useRouter();

  useEffect(() => {
    const token = Cookies.get('auth_token');

    if (!token) {
      router.push('/login');
    }
  }, [router]);
  return (
    <>
      <NextUIProvider>{children}</NextUIProvider>
      <Toaster />
    </>
  );
};

export default Providers;
