

def main():
	in_file = open('wcards.txt')
	str_file = open('wcards_strings.txt', 'w')
	arr_file = open('wcards_array.txt', 'w')
	arr_file.write('<string-array name="white_cards">\n')
	count = 0
	for line in in_file:
		strings = line.split('<>')
		for val in strings:	
			val = val.replace('\'', '\\\'')
			str_id = 'wcard_%d' % count
			str_file.write('<string name="%s">%s</string>\n' % (str_id, val))
			arr_file.write('\t<item>@string/%s</item>\n' % str_id)
			count = count+1
	arr_file.write('</string-array>\n')

if __name__ == "__main__":
	main()
