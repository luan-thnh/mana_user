'use client';

import { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { Card, CardHeader, CardBody, Avatar, Button } from '@nextui-org/react';
import { GetUserResponse } from '@/apis/user';
import { me } from '@/apis/auth';
import toast from 'react-hot-toast';
import dayjs from 'dayjs';
import { useRouter } from 'next/navigation';
import { FiLogOut } from 'react-icons/fi';
import Image from 'next/image';
import { FaFacebookF, FaInstagram, FaLinkedinIn, FaTwitter } from 'react-icons/fa';

export default function Home() {
  const [user, setUser] = useState<GetUserResponse['result'] | null>(null);
  const [loading, setLoading] = useState(true);

  const router = useRouter();

  useEffect(() => {
    const fetchUserDetails = async () => {
      const token = Cookies.get('auth_token');
      if (!token) {
        toast.error('No token found. Please login.');
        setLoading(false);
        return;
      }

      try {
        const userData = await me(token);
        setUser(userData?.result);
        toast.success('User details fetched successfully!');
      } catch (error) {
        console.error('Failed to fetch user details:', error);
        toast.error('Failed to fetch user details.');
      } finally {
        setLoading(false);
      }
    };

    fetchUserDetails();
  }, []);

  const handleLogout = () => {
    Cookies.remove('auth_token', { path: '/' }); // Remove token cookie
    toast.success('Logged out successfully!');
    router.push('/login'); // Redirect to login page
  };

  if (loading) {
    return <div className="flex justify-center items-center h-screen">Loading...</div>;
  }

  if (!user) {
    return <div className="flex justify-center items-center h-screen">No user data found.</div>;
  }

  return (
    <div className="flex justify-center items-center h-screen bg-blue-100">
      <Card className="w-96 text-gray-900 transition-all !duration-400 hover:scale-105 hover:-translate-y-6">
        <CardHeader className="flex flex-col items-stretch p-0">
          <Image
            src="https://random-image-pepebigotes.vercel.app/api/random-image"
            alt="Logo"
            width={384}
            height={100}
            className="h-[100px] object-cover"
          />
          <div className="flex items-start space-x-4 justify-between p-4">
            <div className="flex space-x-4 items-center">
              <Avatar
                src={'https://ui-avatars.com/api/?name=' + user?.firstName + '+' + user?.lastName}
                alt="User Avatar"
                size="lg"
                className="border-2 border-white"
              />
              <div>
                <p className="text-xl font-bold text-blue-600">{`${user?.firstName} ${user?.lastName}`}</p>
                <p className="text-sm">{user?.username}</p>
              </div>
            </div>
            <Button isIconOnly variant="light" className="text-gray-900" onPress={handleLogout}>
              <FiLogOut />
            </Button>
          </div>
        </CardHeader>
        <CardBody className="p-4 flex flex-col items-start space-y-2">
          <div className="flex items-center space-x-2">
            <p className="text-base font-semibold text-gray-800">Birthday:</p>
            <p className="text-base font-medium text-blue-600">{dayjs(user?.birthday).format('MMM D, YYYY')}</p>
          </div>

          <div className="flex mt-4">
            <Button
              isIconOnly
              variant="light"
              className="text-blue-600 hover:text-blue-800 transition-all"
              aria-label="Facebook"
            >
              <FaFacebookF size={16} />
            </Button>
            <Button
              isIconOnly
              variant="light"
              className="text-blue-600 hover:text-blue-800 transition-all"
              aria-label="Twitter"
            >
              <FaTwitter size={16} />
            </Button>
            <Button
              isIconOnly
              variant="light"
              className="text-blue-600 hover:text-blue-800 transition-all"
              aria-label="Instagram"
            >
              <FaInstagram size={16} />
            </Button>
            <Button
              isIconOnly
              variant="light"
              className="text-blue-600 hover:text-blue-800 transition-all"
              aria-label="LinkedIn"
            >
              <FaLinkedinIn size={16} />
            </Button>
          </div>
        </CardBody>
      </Card>
    </div>
  );
}
